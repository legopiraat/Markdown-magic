package io.legopiraat.markdownmagic.parser

import io.legopiraat.markdownmagic.element.MarkdownElement

trait Parser {

  def parse(line: String): MarkdownElement

}
