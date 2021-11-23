package main

import (
	"fmt"
	"net/http"

	"github.com/labstack/echo/v4"
)

func main() {
	e := echo.New()
	fmt.Println("test")
	e.GET("/", getHelloWorld)
	e.Logger.Fatal(e.Start(":1323"))
}

func getHelloWorld(c echo.Context) error {
	return c.String(http.StatusOK, "Hello, World!")
}
