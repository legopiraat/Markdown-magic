package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element.MarkdownListEntry
import io.legopiraat.markdownmagic.element.{Numeric, Dotted}

trait ListParser {

  // Pattern for matching on 1. {text} or 1) {text}
  private[this] val NumericListPattern = "(^\\d[.] |^\\d[)] )(.+)".r
  // Pattern for matching on - {text} or * {text}
  private[this] val DottedListPattern = "(^[-] |^[*] )(.+)".r

  def parseListEntry(line: String): MarkdownListEntry = {
    line match {
      case line if NumericListPattern.matches(line) =>
        MarkdownListEntry(NumericListPattern.findAllIn(line).group(2), Numeric)
      case line if DottedListPattern.matches(line) =>
        MarkdownListEntry(DottedListPattern.findAllIn(line).group(2), Dotted)
    }
  }

  def startsWithListMarker(line: String): Boolean = {
    NumericListPattern.matches(line) || DottedListPattern.matches(line)
  }
}
