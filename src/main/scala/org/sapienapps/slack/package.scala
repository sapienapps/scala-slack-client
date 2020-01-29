package org.sapienapps

package object slack {

  case class SlackAttachment(text: Option[String] = None,
                             color: Option[String] = None,
                             attachment_type: Option[String] = Option("default"),
                             callback_id: Option[String] = None)

  case class SlackMessage(channel: String,
                          text: Option[String] = None,
                          attachments: List[SlackAttachment] = List())

}
