# Useful rsync operations

1) Move AVI files, preserve all attributes, preseerve relative path

        sudo rsync -aviuP --include '*/' --include '*[Aa][Vv][Ii]' \
                   --exclude '*' --prune-empty-dirs --remove-source-files \
                   source/ destination/
