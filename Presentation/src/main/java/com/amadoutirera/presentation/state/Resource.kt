package com.amadoutirera.presentation.state

class Resource <out DATA, out MESSAGE> constructor(val status: ResourceState, val data : DATA?, val message: MESSAGE?)