package io.legopiraat.markdownmagic

import java.io.{BufferedReader, FileReader}

import io.legopiraat.markdownmagic.element._
import io.legopiraat.markdownmagic.parser.{HeadingParser, ListParser}

import scala.annotation.tailrec

object MarkdownConverter extends ListParser with HeadingParser {

  def parse(filePath: String): Unit = {
    val bufferedReader: BufferedReader = new BufferedReader(new FileReader(filePath))

    bufferedReader.lines().forEach(line => {
     val element = line match {
        case line if line.startsWith("#") => parseHeading(line)
        case line if startsWithListMarker(line) => parseListEntry(line)
        case _ => parseLine(line)
      }
      
      println(element.toString)
    })
  }
  
  val HyperLinkPattern = "\\[(.*?)\\]\\((.*?)\\).+".r
  val ImagePattern = "!\\[(.*?)\\]\\((.*?)\\).+".r

  private[this] def parseLine(line: String): List[MarkdownElement] = {
    val tokens = line.split("\\s+")
    
    @tailrec
    def parseTokens(tokens: List[String], lineElements: List[MarkdownElement]): List[MarkdownElement] = {
      tokens match {
        case token :: rest => {
          // Check what token it is.
          // Add it to the elements and pass on the rest.
          
          parseTokens(rest, lineElements :+ MarkdownText(""))
        }
        case Nil => lineElements
      }
    }
    
    //ImagePattern.findAllIn(line).groupCount
    
    tokens.map(token => {
      token match {
        case token if token.startsWith("**") || token.startsWith("__") => // Start bold
        case token if token.startsWith("*") || token.startsWith("_")  => // Start Italic
        case token if token.endsWith("**") || token.endsWith("__")  => // End bold
        case token if token.endsWith("*") || token.endsWith("_") => // End Italic
        case token if ImagePattern.matches(token) => {
          val groups = ImagePattern.findAllIn(line)
          MarkdownImage(groups.group(2), groups.group(1))
        }  // Image
        case token if HyperLinkPattern.matches(token) => {
          val groups = HyperLinkPattern.findAllIn(line)
          MarkdownImage(groups.group(2), groups.group(1))
        }
        case _ => token
      }
      
      if(token.startsWith("**")) {
        // this case its bold
      }
    })
    
    
    List(MarkdownImage("", null))
  }
  
  private[this] def containsImageMarker(line: String): Boolean = {
    true
  }
}