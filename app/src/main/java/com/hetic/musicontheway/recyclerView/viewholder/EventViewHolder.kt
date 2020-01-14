package com.hetic.musicontheway.RecyclerView

class EventViewHolder {
    (itemView: View) : FastAdapter.ViewHolder<MovieItem>(itemView) {
        override fun bindView(item: MovieItem, payloads: MutableList<Any>) {
            val movie = item.movie

            itemView.movieRowTitleTextView.text = movie.title
            itemView.movieRowNumberTextView.text = movie.number.toString()
        }

        override fun unbindView(item: MovieItem) {
            itemView.movieRowTitleTextView.text = null
            itemView.movieRowNumberTextView.text = null
        }
    }
}