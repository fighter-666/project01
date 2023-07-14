package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ComponentsBinding
import com.example.myapplication.databinding.ComponentsFragmentBinding
import com.example.myapplication.databinding.FragmentLabBinding

class LabFragment : Fragment(){
    private  var _binding : FragmentLabBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLabBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {
        fun newInstance(text: String): LabFragment {
            val args = Bundle()
            args.putString("text", text)
            val fragment = LabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(context,3)
        binding.recyclerView.adapter = MyAdapter()
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }


     class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
         inner class MyViewHolder(binding: ComponentsBinding): RecyclerView.ViewHolder(binding.root) {
            val image: ImageView = binding.itemImage
             val title: TextView = binding.itemTitle
             val message : TextView = binding.itemMessage
        }

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
             val binding =
                 ComponentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return MyViewHolder(binding)
         }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//            holder.binding.itemImage.setImageResource(R.drawable.bgs)
//            holder.binding.itemTitle.text = "架构师"
//            holder.binding.itemMessage.text = "哇哈哈"
            holder.image.setImageResource(R.drawable.images)
            holder.title.text = "冰箱"
            holder.message.text = "洗衣机"
        }

        override fun getItemCount(): Int {
            return 6
        }
    }

    class MyViewHolder(binding: ComponentsBinding): RecyclerView.ViewHolder(binding.root) {

    }
}


