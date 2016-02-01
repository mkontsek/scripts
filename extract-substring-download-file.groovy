import static groovy.io.FileType.FILES

/**
 * Script for extracting matching substring from html,css and js lines (in this case, "images/...") and downloading to specific folder
 */

def baseDir = '/opt/basedir/'
def baseUrl = 'http://www.foo.bar/world/'

def files = []
new File(baseDir).eachFileRecurse(FILES) {
    if (it.name.endsWith('.css') || it.name.endsWith('.js') || it.name.endsWith('.html'))
        files.push(it)
}

def urls = []
files.each {
    it.eachLine { line ->
        def m = line =~ /images\/.*\..../
        if (m?.find())
            urls.push(baseUrl + m.group(0))
    }
}

urls.each {
    try {
        new File("$baseDir/images/" + it.split('/').last()).withOutputStream { out ->
            out << new URL(it).openStream()
        }
    } catch (e) {
        println "No file for $it"
    }
}

return