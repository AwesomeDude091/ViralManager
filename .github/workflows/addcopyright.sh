for x in $*; do
head -$COPYRIGHTLEN $x | diff .github/workflows/copyright.txt - || ( ( cat copyright.txt; echo; cat $x) > /tmp/file;
mv /tmp/file $x )
done
