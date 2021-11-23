package main

import (
	"fmt"
	"net/http"

	"github.com/go-redis/redis/v8"
	"github.com/labstack/echo/v4"
)

func main() {
	rdb := redis.NewClient(&redis.Options{
		Addr:     "localhost:6379",
		Password: "", // no password set
		DB:       0,  // use default DB
	})

	fmt.Println(rdb)

	e := echo.New()
	fmt.Println("test")
	e.GET("/", getHelloWorld)
	e.Logger.Fatal(e.Start(":1323"))
}

func getHelloWorld(c echo.Context) error {
	return c.String(http.StatusOK, "Hello, World!")
}
