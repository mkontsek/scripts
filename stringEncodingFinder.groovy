def origStr = "Saška v čiernych šatoch je výborná spoločnosť, že úplne, ľudia, i môže Ťa ískať. Čislo, kŕdel vrán útočí, isté je."

java.nio.charset.Charset.availableCharsets().each {
    def str
    def encoding = it.toString().split('=')[0]
    try {
        str = new String(origStr.getBytes(), encoding)
    } catch(Exception e) {
        str = "Unsupported encoding $encoding"
    }
    println str + "; $encoding"
}  

println ""
