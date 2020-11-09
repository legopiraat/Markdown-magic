package io.legopiraat.markdownmagic

import java.io.{BufferedReader, FileReader}

import io.legopiraat.markdownmagic.element._
import io.legopiraat.markdownmagic.parser.{HeadingParser, LineParser, ListParser}

import scala.annotation.tailrec

object MarkdownConverter {
  def apply(): MarkdownConverter = {
    val lineParser = LineParser()

    new MarkdownConverter(ListParser(lineParser), HeadingParser(lineParser), lineParser)
  }
}

class MarkdownConverter(listParser: ListParser, headingParser: HeadingParser, lineParser: LineParser) {

  def parse(filePath: String): Unit = {
    val bufferedReader: BufferedReader = new BufferedReader(new FileReader(filePath))

    bufferedReader.lines().forEach(line => {
      val element = line match {
        case line if line.isBlank => MarkdownNewLine
        case line if headingParser.startsWithHeadingIndicator(line) => headingParser.parse(line)
        case line if listParser.startsWithListMarker(line) => listParser.parse(line)
        case _ => lineParser.parse _
      }

      println(element.toString)
    })
  }
}