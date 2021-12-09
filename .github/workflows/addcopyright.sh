# shellcheck disable=SC2048
for x in $*; do
  if ! grep -q "$x" "/home/runner/work/ViralManager/ViralManager/.github/workflows/copyright.txt"; then
    head -$COPYRIGHTLEN $x | diff /home/runner/work/ViralManager/ViralManager/.github/workflows/copyright.txt - || ( ( cat /home/runner/work/ViralManager/ViralManager/.github/workflows/copyright.txt; echo; cat $x) > /tmp/file;
    mv /tmp/file $x )
  fi
done
