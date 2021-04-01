# ApplAlgInterpreter Binaries

Weekly builds for [Prof. Dr. Fridtjof Toenniessen's ApplAlgInterpreter](https://www.hdm-stuttgart.de/kontakt/suche_ergebnis_liste?Id=165).

![hydrun CI](https://github.com/pojntfx/appl-alg-interpreter-binaries/workflows/hydrun%20CI/badge.svg)

## Installation

Binaries of the latest known release are built weekly and uploaded to [GitHub releases](https://github.com/pojntfx/appl-alg-interpreter-binaries/releases).

You can install them like so:

```shell
$ curl -L -o /tmp/iappl https://github.com/pojntfx/appl-alg-interpreter-binaries/releases/download/latest/iappl.linux-$(uname -m)
$ sudo install /tmp/iappl /usr/local/bin
```

## Usage

After installation, you can run APL code like so:

```shell
$ iappl examples/Quersumme.apl
```

## License

ApplAlgInterpreter Binaries (c) 2021 Felicitas Pojtinger and contributors

SPDX-License-Identifier: AGPL-3.0
