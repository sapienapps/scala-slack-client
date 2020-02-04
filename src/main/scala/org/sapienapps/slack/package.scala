package org.sapienapps

package object slack {

  case class SlackAttachment(text: Option[String] = None,
                             color: Option[String] = None,
                             attachment_type: Option[String] = Option("default"),
                             callback_id: Option[String] = None)

  case class SlackMessage(channel: String,
                          text: Option[String] = None,
                          attachments: List[SlackAttachment] = List())

  case class SlackResponse(channel: Option[String],
                           text: Option[String],
                           attachments: Option[List[SlackAttachment]],
                           // Error/Warnings Below:
                           ok: Option[Boolean],
                           error: Option[String],
                           warning: Option[String],
                           response_metadata: Option[SlackResponseMetadata]
                          )

  case class SlackResponseMetadata(warnings: Option[List[String]],
                                   errors: Option[List[String]])

  case class Response(jsonString: String,
                      code: Int,
                      response: Option[SlackResponse])

}
