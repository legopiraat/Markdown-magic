package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element._

import scala.annotation.tailrec

object LineParser {
  def apply(): LineParser = new LineParser()
}

class LineParser extends Parser {

  private[this] val HyperLinkPattern = "\\[(.*?)\\]\\((.*?)\\)(.*)".r
  private[this] val ImagePattern = "!\\[(.*?)\\]\\((.*?)\\)(.*)".r

  def parse(line: String): MarkdownLine = {

    val tokens = line.split("\\s+").toList

    @tailrec
    def parseTokens(tokens: List[String], textElements: List[MarkdownTextElement]): List[MarkdownTextElement] = {
      tokens match {
        case token :: rest =>
          val element = parseToken(token)
          parseTokens(rest, textElements :+ element)
        case Nil => textElements
      }
    }

    MarkdownLine(parseTokens(tokens, Nil))
  }

  private def parseToken(token: String): MarkdownTextElement = {
    token match {
      case element if element.startsWith("**") && element.endsWith("**") => MarkdownText(element.replaceAll("\\*", ""), Bold)
      case element if element.startsWith("*") && element.endsWith("*") => MarkdownText(element.replaceAll("\\*", ""), Italic)
      case element if HyperLinkPattern.matches(element) =>
        val groups = HyperLinkPattern.findAllIn(element)
        MarkdownHyperLink(MarkdownText(groups.group(1).concat(groups.group(3)), Clear), groups.group(2))
      case element if ImagePattern.matches(element) =>
        val groups = ImagePattern.findAllIn(element)
        MarkdownImage(MarkdownText(groups.group(1).concat(groups.group(3)), Clear), groups.group(2))
      case _ => MarkdownText(token, Clear)
    }
  }
}
