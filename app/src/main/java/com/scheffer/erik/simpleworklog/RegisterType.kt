package com.scheffer.erik.simpleworklog

enum class RegisterType(private val stringId: Int) {
    CLOCK_IN(R.string.clock_in), CLOCK_OUT(R.string.clock_out);

    override fun toString(): String {
        return MyApplication.context.getString(this.stringId)
    }
}