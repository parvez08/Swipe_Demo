package com.example.swipedemo.utils

import android.content.Context
import android.util.Log

open class LoadingUtils {

    companion object {
        private val TAG = "LoadingUtils"

        private var jarvisLoader: SwipeLoader? = null
        fun showDialog(
            context: Context?,
            isCancelable: Boolean
        ) {
            hideDialog()
            if (context != null) {
                try {
                    jarvisLoader = SwipeLoader(context)
                    jarvisLoader?.let { jarvisLoader ->
                        jarvisLoader.setCanceledOnTouchOutside(true)
                        jarvisLoader.setCancelable(isCancelable)
                        jarvisLoader.show()
                    }

                } catch (e: Exception) {
                    Log.e(TAG, e.toString())
                }
            }
        }

        fun isShowing(): Boolean {
            if (jarvisLoader == null) {
                return false;
            }

            return jarvisLoader?.isShowing!!
        }

        fun hideDialog() {
            if (jarvisLoader != null && jarvisLoader?.isShowing!!) {
                jarvisLoader = try {
                    jarvisLoader?.dismiss()
                    null
                } catch (e: IllegalArgumentException) {
                    Log.e(TAG, e.toString())
                    null
                } catch (e: Exception) {
                    Log.e(TAG, e.toString())
                    null
                }
            }
        }

    }


}