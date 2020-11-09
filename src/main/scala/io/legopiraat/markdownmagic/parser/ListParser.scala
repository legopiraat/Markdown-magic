package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element._

object ListParser {
  def apply(lineParser: LineParser): ListParser = {
    new ListParser(lineParser)
  }
}

class ListParser(lineParser: LineParser) {

  // Pattern for matching on 1. {text} or 1) {text}
  private[this] val NumericListPattern = "(^\\d[.] |^\\d[)] )(.+)".r
  // Pattern for matching on - {text} or * {text}
  private[this] val DottedListPattern = "(^[-] |^[*] )(.+)".r

  def parseListEntry(line: String): MarkdownListEntry = {
    // TODO: Update to have the rest of the line parsed.

    line match {
      case line if NumericListPattern.matches(line) =>
        MarkdownListEntry(MarkdownLine(List(MarkdownText(NumericListPattern.findAllIn(line).group(2), Clear))), Numeric)
      case line if DottedListPattern.matches(line) =>
        MarkdownListEntry(MarkdownLine(List(MarkdownText(DottedListPattern.findAllIn(line).group(2), Clear))), Dotted)
    }
  }

  def startsWithListMarker(line: String): Boolean = {
    NumericListPattern.matches(line) || DottedListPattern.matches(line)
  }
}
