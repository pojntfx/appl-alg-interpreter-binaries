# ApplAlgInterpreter Binaries

Weekly builds and source mirror for [Prof. Dr. Fridtjof Toenniessen's ApplAlgInterpreter](https://www.hdm-stuttgart.de/kontakt/suche_ergebnis_liste?Id=165).

![hydrun CI](https://github.com/pojntfx/appl-alg-interpreter-binaries/workflows/hydrun%20CI/badge.svg)

## Installation

Binaries of the latest known release are built weekly and uploaded to [GitHub releases](https://github.com/pojntfx/appl-alg-interpreter-binaries/releases).

You can install them like so:

```shell
$ curl -L -o /tmp/iappl https://github.com/pojntfx/appl-alg-interpreter-binaries/releases/download/latest/iappl.linux-$(uname -m)
$ sudo install /tmp/iappl /usr/local/bin
```

## Usage

After installation, you can run ApplAlgInterpreter programs like so:

```shell
$ iappl examples/Quersumme.apl
```

Additionally, you can set the following environment variables for additional debugging output:

| Variable                     | Description                            |
| ---------------------------- | -------------------------------------- |
| `IAPPL_DEBUG_PARSER=true`    | Show parser debug output               |
| `IAPPL_PRINT_FUNCTIONS=true` | Show function trace                    |
| `IAPPL_COUNT_FUNCTIONS=true` | Count the amount of executed functions |

Use them like so:

```shell
$ IAPPL_DEBUG_PARSER=true IAPPL_PRINT_FUNCTIONS=true IAPPL_COUNT_FUNCTIONS=true iappl examples/Quersumme.apl
```

See [examples](https://github.com/pojntfx/appl-alg-interpreter-binaries/blob/main/examples) for more example programs and grammar.

## License

ApplAlgInterpreter Binaries (c) 2021 Felicitas Pojtinger and contributors

SPDX-License-Identifier: AGPL-3.0
