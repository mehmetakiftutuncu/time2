package com.github.mehmetakiftutuncu.time2.models

import java.time.ZonedDateTime

import io.circe.{Decoder, Encoder}

final case class Thing(id: Long,
                       what: String,
                       when: ZonedDateTime,
                       who: User) extends Model[Thing] {
  override val jsonEncoder: Encoder[Thing] = {
    implicit val userJsonEncoder: Encoder[User] = who.jsonEncoder

      Encoder.forProduct4("id", "what", "when", "who")(t => (t.id, t.what, t.when, t.who))
  }
}

object Thing extends JsonSupport[Thing] {
  override val jsonDecoder: Decoder[Thing] = {
    implicit val userJsonDecoder: Decoder[User] = User.jsonDecoder

    Decoder.forProduct4("id", "what", "when", "who")(Thing.apply)
  }
}
