package org.sapienapps.slack

import scalaj.http.Http

sealed trait MessageStatus[T] {
  def value: T
}

object GOOD extends MessageStatus[String] {
  def value = "good"
}

object WARNING extends MessageStatus[String] {
  def value = "warning"
}

object DANGER extends MessageStatus[String] {
  def value = "danger"
}

object DEFAULT extends MessageStatus[String] {
  def value = ""
}

object RANDOM extends MessageStatus[String] {
  def value = "random"
}

trait SlackService {

  def toJsonString(msg: SlackMessage): String
  def channel: String
  def token: String

  def message(message: String,
              msgFormat: String = "text",
              color: MessageStatus[String] = DEFAULT,
              notify: Boolean = false): Any = {
    val colorOpt: Option[String] = Option(color.value)

    val attachment: SlackAttachment = SlackAttachment(text = Option(message), color = colorOpt)

    val msg = SlackMessage(
      channel = channel,
      text = None,
      attachments = List[SlackAttachment](attachment)
    )

    val text = toJsonString(msg)

    val code = Http("https://slack.com/api/chat.postMessage")
      .postData(text)
      .header("Authorization", "Bearer " + token)
      .header("content-type", "application/json").asString.code
    code
  }
}
