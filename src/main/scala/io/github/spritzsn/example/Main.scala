package io.github.spritzsn.example

import io.github.spritzsn.spritz.{Request, Response, Server, responseTime, HandlerResult}
import io.github.spritzsn.body_parser.{json, urlencoded}
import io.github.spritzsn.cors
import io.github.spritzsn.serve_static
import io.github.spritzsn.logger
import io.github.spritzsn.compression

@main def run(): Unit =
  Server("ExampleServer/1.0") { app =>
    app
      .use(json())
      .use(urlencoded())
      .use(cors())
      .use(compression())
      .use(responseTime())
      .use(logger("dev" /*, "access.log"*/ ))
      .use("/project", serve_static("project"))
      //      .use((req, res) => {
      //        println(req.headers)
      //        res.action(println(res.headers))
      //        HandlerResult.Next
      //      })
      .get("/", (req, res) => res.send("hello"))
      .get(
        "/long",
        (_, res) =>
          res.send(
            """
              |The result is a clear indication of a compressed result, in the Content-Encoding: gzip HTTP header.
              |You’ll also notice that the output of the page is no longer human readable, as it’s a compressed page
              |that is being sent. The see the output, you would have to gunzip it.
              |""".stripMargin,
          ),
      )
      .post("/", (req, res) => res.send(req.body))
      .post("/empty", (req, res) => res.status(204))
    app.listen(3000)
    println("listening")
  }
