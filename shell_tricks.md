# Shell Tricks
Various shell patterns that I find useful.

1) Replace a substring in a bunch of files:
    ```
    sed -i -e 's/oldsubstring/newsubstring/g' *json
    ```
2) Switch default version of an app (e.g. emacs):
    ```
    sudo update-alternatives --config emacs
    ```
