package com.example.todo_kotlin

object DataObject {
    var listdata = mutableListOf<CardViewModel>()
    fun setData(task: String, priority: String) {
        listdata.add(CardViewModel(task,priority))
    }

    fun getAllData(): List<CardViewModel> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int) : CardViewModel{
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,task:String,priority:String)
    {
        listdata[pos].taskContent=task
        listdata[pos].priority=priority
    }

}