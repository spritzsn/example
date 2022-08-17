package io.github.spritzsn.example

import io.github.spritzsn.spritz.{Request, Response, Server, cors, responseTime}
import io.github.spritzsn.body_parser.JSON

@main def run(): Unit =
  Server("ExampleServer/1.0") { app =>
    app
      .use(JSON())
      .use(cors())
      .use(responseTime())
      .get("/", (_: Request, res: Response) => res.send("hello"))
      .post("/", (req: Request, res: Response) => res.send(req.body))
    app.listen(3000)
    println("listening")
  }
