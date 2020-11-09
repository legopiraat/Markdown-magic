package io.legopiraat.markdownmagic

import java.io.{BufferedReader, FileReader}

import io.legopiraat.markdownmagic.element._
import io.legopiraat.markdownmagic.parser.{HeadingParser, LineParser, ListParser}

import scala.annotation.tailrec

object MarkdownConverter {
  def apply(): MarkdownConverter = {
    val lineParser = LineParser()
    new MarkdownConverter(ListParser(lineParser), HeadingParser(lineParser))
  }
}

class MarkdownConverter(listParser: ListParser, headingParser: HeadingParser) {

  def parse(filePath: String): Unit = {
    val bufferedReader: BufferedReader = new BufferedReader(new FileReader(filePath))

    bufferedReader.lines().forEach(line => {
      val element = line match {
        case line if line.isBlank => MarkdownNewLine
        case line if headingParser.startsWithHeadingIndicator(line) => headingParser.parseHeading(line)
        case line if listParser.startsWithListMarker(line) => listParser.parseListEntry(line)
        case _ => MarkdownLine(parseLine(line))
      }

      println(element.toString)
    })
  }

  val HyperLinkPattern = "\\[(.*?)\\]\\((.*?)\\).+".r
  val ImagePattern = "!\\[(.*?)\\]\\((.*?)\\).+".r

  private[this] def parseLine(line: String): List[MarkdownTextElement] = {
    val tokens = line.split("\\s+")

    @tailrec
    def parseTokens(tokens: List[String], lineTextElements: List[MarkdownTextElement]): List[MarkdownTextElement] = {
      tokens match {
        case token :: rest => {
          // Check what token it is.
          // Add it to the elements and pass on the rest.

          parseTokens(rest, lineTextElements :+ MarkdownText("", Clear))
        }
        case Nil => lineTextElements
      }
    }

    Nil

    //ImagePattern.findAllIn(line).groupCount

//    tokens.map(token => {
//      token match {
//        case token if token.startsWith("**") || token.startsWith("__") => // Start bold
//        case token if token.startsWith("*") || token.startsWith("_") => // Start Italic
//        case token if token.endsWith("**") || token.endsWith("__") => // End bold
//        case token if token.endsWith("*") || token.endsWith("_") => // End Italic
//        case token if ImagePattern.matches(token) => {
//          val groups = ImagePattern.findAllIn(line)
//          MarkdownImage(MarkdownText(groups.group(2)), groups.group(1))
//        } // Image
//        case token if HyperLinkPattern.matches(token) => {
//          val groups = HyperLinkPattern.findAllIn(line)
//          MarkdownImage(MarkdownText(groups.group(2)), groups.group(1))
//        }
//        case _ => token
//      }
//
//      if (token.startsWith("**")) {
//        // this case its bold
//      }
//    })
  }

  private[this] def containsImageMarker(line: String): Boolean = {
    true
  }
}