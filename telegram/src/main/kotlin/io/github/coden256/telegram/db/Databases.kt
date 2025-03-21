package io.github.coden256.telegram.db

import org.mapdb.DB
import org.mapdb.DBMaker

fun db(name: String): DB {
    return DBMaker.fileDB(name)
        .fileMmapEnableIfSupported()
        .closeOnJvmShutdown()
        .transactionEnable()
        .make()

}