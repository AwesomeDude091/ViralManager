for x in $*; do
head -$COPYRIGHTLEN $x | diff /home/runner/work/ViralManager/ViralManager/.github/workflows/copyright.txt - || ( ( cat /home/runner/work/ViralManager/ViralManager/.github/workflows/copyright.txt; echo; cat $x) > /tmp/file;
mv /tmp/file $x )
done