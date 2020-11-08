package io.legopiraat.markdownmagic.element

trait MarkdownElement

case object MarkdownNewLine extends MarkdownElement

case class MarkdownHeading(txt: String, headingLevel: Int) extends MarkdownElement
case class MarkdownListEntry(txt: String, listType: ListType) extends MarkdownElement
case class MarkdownText(txt: String) extends MarkdownElement
case class MarkdownHyperLink(txt: String, uri: String) extends MarkdownElement
case class MarkdownImage(txt: String, uri: String) extends MarkdownElement
