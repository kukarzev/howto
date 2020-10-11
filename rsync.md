# Useful rsync operations

1) Sync updates from `source` to `archive`, ignore permissions, group and owner, preserve time (`-t`). Dry run (`-n`). 
   Delete files in destination that were deleted in source at the end (`--delete-after`). 
   Exclude `/deleted` directory in the root of destination because this is where we move deleted files for future review.
   Dump the results of dry run to log file:
   convenient to check/move the deletions before actual run.
   
        sudo rsync -rlDvt --delete-after \
                   --exclude '/deleted'
                   /source/Photos/ /archive/Photos/ \
                   -n >rsync3.log 2>&1 &

1) Move AVI files, preserve all attributes, preseerve relative path

        sudo rsync -aviuP --include '*/' --include '*[Aa][Vv][Ii]' \
                   --exclude '*' --prune-empty-dirs --remove-source-files \
                   source/ destination/
