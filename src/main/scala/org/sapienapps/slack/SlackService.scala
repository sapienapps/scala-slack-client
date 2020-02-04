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

  /**
   * Hook to convert the Request Message to a JSON string, allowing the consumer to use the JSON
   * lib of their choice.
   *
   * @param msg - Message Object that Needs Conversion
   * @return
   */
  def toJsonString(msg: SlackMessage): String

  /**
   * Hook to convert the String Response to a SlackResponse, allowing the consumer to use the JSON
   * lib of their choice.
   *
   * @param string - String Response that needs Conversion to SlackResponse Case Class
   * @return
   */
  def toObject(string: String): Option[SlackResponse] = None

  /**
   * Channel for this Slack Client Instance
   *
   * @return
   */
  def channel: String

  /**
   * Slack API Token for
   *
   * @return
   */
  def token: String

  def message(message: String,
              msgFormat: String = "text",
              color: MessageStatus[String] = DEFAULT,
              notify: Boolean = false): Response = {
    val colorOpt: Option[String] = Option(color.value)

    val attachment: SlackAttachment = SlackAttachment(text = Option(message), color = colorOpt)

    val msg = SlackMessage(
      channel = channel,
      text = None,
      attachments = List[SlackAttachment](attachment)
    )

    val text = toJsonString(msg)

    val request = Http("https://slack.com/api/chat.postMessage")
      .postData(text)
      .header("Authorization", "Bearer " + token)
      .header("content-type", "application/json").asString

    Response(request.body, request.code, toObject(request.body))
  }
}
