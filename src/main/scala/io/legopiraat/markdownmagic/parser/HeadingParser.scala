package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element._

object HeadingParser {
  def apply(lineParser: LineParser): HeadingParser = {
    new HeadingParser(lineParser)
  }
}

class HeadingParser(lineParser: LineParser) {

  private[this] val headingIndicator = '#'

  def parseHeading(line: String): MarkdownHeading = {
    val headingLevel = determineHeadingLevel(line)
    val remainingText = getLineText(line, headingLevel)

    //TODO: Line parsing
    MarkdownHeading(MarkdownLine(List(MarkdownText(remainingText, Clear))), headingLevel)
  }

  def startsWithHeadingIndicator(line: String): Boolean = {
    line.startsWith(headingIndicator.toString)
  }

  private[this] def determineHeadingLevel(line: String): Int = {
    line.takeWhile(c => c.equals(headingIndicator)).length
  }

  private[this] def getLineText(line: String, offset: Int): String = {
    line.substring(offset, line.length).trim
  }
}
