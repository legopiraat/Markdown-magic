import com.legopiraat.markdownmagic.MarkdownConverter

object Main {

  def main(args: Array[String]): Unit = {
    val filePath = "C:\\Users\\Legopiraat\\Programming\\Projects\\stryker\\stryker4s\\README.md"

    MarkdownConverter.parse(filePath)
  }

}
