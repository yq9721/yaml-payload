javac src/artsploit/AwesomeScriptEngineFactory.java -cp ./lib/*
javac src/artsploit/Tunnel.java -cp ./lib/*
javac src/artsploit/GameInfo.java -cp ./lib/*
jar -cvfm yaml-payload.jar src/META-INF/MANIFEST.MF -C src/ .
