package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element.{Clear, MarkdownLine, MarkdownText, MarkdownTextElement}

import scala.annotation.tailrec

object LineParser {
  def apply(): LineParser = new LineParser()
}

class LineParser extends Parser {

  private[this] val HyperLinkPattern = "\\[(.*?)\\]\\((.*?)\\).+".r
  private[this] val ImagePattern = "!\\[(.*?)\\]\\((.*?)\\).+".r

  def parse(line: String): MarkdownLine = {

    val tokens = line.split("\\s+")

    //@tailrec
    def parseTokens(tokens: List[String], textElements: List[MarkdownTextElement]) = {

      textElements
    }



    MarkdownLine(List(MarkdownText(line, Clear)))
  }

//  private[this] def parseLine(line: String): List[MarkdownTextElement] = {
//    val tokens = line.split("\\s+")

//    @tailrec
//    def parseTokens(tokens: List[String], lineTextElements: List[MarkdownTextElement]): List[MarkdownTextElement] = {
//      tokens match {
//        case token :: rest => {
//          // Check what token it is.
//          // Add it to the elements and pass on the rest.
//
//          parseTokens(rest, lineTextElements :+ MarkdownText("", Clear))
//        }
//        case Nil => lineTextElements
//      }
//    }
//
//    Nil

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
