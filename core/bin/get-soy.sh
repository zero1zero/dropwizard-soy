BIN_DIR="`dirname \"$0\"`"

LIB_DIR="`( cd \"$BIN_DIR/../\" && mkdir -p lib && cd lib && pwd )`"

VERSION=2012-12-21
ARTIFACT_NAME=closure-templates-for-java-$VERSION.zip
ARTIFACT_FULL=$LIB_DIR/$ARTIFACT_NAME

curl https://closure-templates.googlecode.com/files/$ARTIFACT_NAME > $ARTIFACT_FULL
mkdir $LIB_DIR/unzipped
unzip $ARTIFACT_FULL -d $LIB_DIR/unzipped
rm $ARTIFACT_FULL

mv $LIB_DIR/unzipped/separate-jars/soy-excluding-deps-$VERSION.jar $LIB_DIR/com/google/template/soy/$VERSION/soy-$VERSION.jar
