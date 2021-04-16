# spark-example

docker build . -t macorin/spark && docker run -v "$HOME/.m2":/root/.m2 -e BASE_URL=http://hiring.axreng.com/ -p 4567:4567 --rm macorin/spark
