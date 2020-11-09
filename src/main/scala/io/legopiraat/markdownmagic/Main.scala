package io.legopiraat.markdownmagic

object Main {

  def main(args: Array[String]): Unit = {
    val filePath = "C:\\Users\\Legopiraat\\Programming\\Projects\\Markdown-magic\\src\\main\\resources\\testmarkdown.md"

    MarkdownConverter().parse(filePath)
  }

}
