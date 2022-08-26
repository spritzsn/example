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
      //      .use((req: Request, res: Response) => {
      //        println(req.headers)
      //        res.action(println(res.headers))
      //        HandlerResult.Next
      //      })
      .get("/", (req: Request, res: Response) => res.send("hello"))
      .get(
        "/long",
        (_: Request, res: Response) =>
          res.send(
            """
              |The result is a clear indication of a compressed result, in the Content-Encoding: gzip HTTP header.
              |You’ll also notice that the output of the page is no longer human readable, as it’s a compressed page
              |that is being sent. The see the output, you would have to gunzip it.
              |""".stripMargin,
          ),
      )
      .post("/", (req: Request, res: Response) => res.send(req.body))
      .post("/empty", (req: Request, res: Response) => res.status(204))
    app.listen(3000)
    println("listening")
  }
