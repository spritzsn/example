package io.github.spritzsn.example

import io.github.spritzsn.spritz.{Request, Response, Server, responseTime}
import io.github.spritzsn.body_parser.{urlencoded, JSON}
import io.github.spritzsn.cors
import io.github.spritzsn.serve_static

@main def run(): Unit =
  Server("ExampleServer/1.0") { app =>
    app
      .use(JSON())
      .use(urlencoded())
      .use(cors())
      .use(responseTime())
      .use("/project", serve_static("project"))
      .get("/", (_: Request, res: Response) => res.send("hello"))
      .post("/", (req: Request, res: Response) => res.send(req.body))
    app.listen(3000)
    println("listening")
  }
