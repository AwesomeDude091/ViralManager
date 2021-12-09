for x in $*; do
head -$COPYRIGHTLEN $x | diff /home/runner/work/ViralManager/ViralManager/.github/workflows/copyright-html.txt - || ( ( cat /home/runner/work/ViralManager/ViralManager/.github/workflows/copyright-html.txt; echo; cat $x) > /tmp/file;
mv /tmp/file $x )
done
