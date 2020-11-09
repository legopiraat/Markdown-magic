package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element._

object HeadingParser {
  def apply(lineParser: LineParser): HeadingParser = {
    new HeadingParser(lineParser)
  }
}

class HeadingParser(lineParser: LineParser) extends Parser {

  private[this] val headingIndicator = '#'

  def parse(line: String): MarkdownHeading = {
    val headingLevel = determineHeadingLevel(line)
    val remainingText = getLineText(line, headingLevel)

    MarkdownHeading(lineParser.parse(remainingText), headingLevel)
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
