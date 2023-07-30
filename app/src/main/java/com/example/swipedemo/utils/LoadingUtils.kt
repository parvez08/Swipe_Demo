package com.example.swipedemo.utils

import android.content.Context
import android.util.Log

open class LoadingUtils {

    companion object {
        private const val TAG = "LoadingUtils"

        private var swipeLoader: SwipeLoader? = null
        fun showDialog(
            context: Context?,
            isCancelable: Boolean
        ) {
            hideDialog()
            if (context != null) {
                try {
                    swipeLoader = SwipeLoader(context)
                    swipeLoader?.let { jarvisLoader ->
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
            if (swipeLoader == null) {
                return false;
            }

            return swipeLoader?.isShowing!!
        }

        fun hideDialog() {
            if (swipeLoader != null && swipeLoader?.isShowing!!) {
                swipeLoader = try {
                    swipeLoader?.dismiss()
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