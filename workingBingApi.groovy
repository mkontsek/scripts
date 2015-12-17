@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )

import groovy.json.*
import groovyx.net.http.*

def jsonSlurper = new JsonSlurper()


String bingUrl = "https://api.datamarket.azure.com/Bing/Search/v1/Web?Query=%27xbox%27" 
String accountKeyEnc = ":.....F44".bytes.encodeBase64().toString();

def remote = new HTTPBuilder(bingUrl)
remote.request(Method.GET, ContentType.JSON) { req ->
    headers.'Authorization' = "Basic ${accountKeyEnc}"

    response.success = { resp, json ->
        def jsonOut = JsonOutput.toJson(json)
 
        println JsonOutput.prettyPrint(jsonOut)
    }
}