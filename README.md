# Spring Logging Level Changer

## Overview

Ability to change the logging level for a specific thread by passing the minimum logging level as an HTTP header. 

This uses Spring `RequestContextHolder` to lookup the current HttpServletRequest and obtain the passed header, as such, this will not work in a non-servlet environment / request thread.

## Usage

Pass the header `X-Logging-Level` with the value of either TRACE, DEBUG, INFO, WARN, ERROR to prevent the logging of levels lower than that passed. 

EG `curl --header "X-Logging-Level: WARN" localhost:10101`

Note - this will not override the configured logging level of the logger. So if the default level for a logger is WARN, setting the header to INFO will have no effect.

 



