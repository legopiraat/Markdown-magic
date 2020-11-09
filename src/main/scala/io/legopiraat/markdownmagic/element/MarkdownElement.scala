package io.legopiraat.markdownmagic.element

trait MarkdownElement

case object MarkdownNewLine extends MarkdownElement
case class MarkdownHeading(text: MarkdownLine, headingLevel: Int) extends MarkdownElement
case class MarkdownListEntry(text: MarkdownLine, listType: ListType) extends MarkdownElement
case class MarkdownLine(line: List[MarkdownTextElement])

trait MarkdownTextElement
case class MarkdownText(text: String, emphasisType: EmphasisType) extends MarkdownTextElement
case class MarkdownHyperLink(text: MarkdownText, uri: String) extends MarkdownTextElement
case class MarkdownImage(text: MarkdownText, uri: String) extends MarkdownTextElement