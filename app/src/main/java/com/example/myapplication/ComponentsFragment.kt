package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ComponentsBinding
import com.example.myapplication.databinding.ComponentsFragmentBinding

class ComponentsFragment : Fragment(){
    private  var _binding : ComponentsFragmentBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ComponentsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    companion object {
        fun newInstance(text: String): ComponentsFragment {
            val args = Bundle()
            args.putString("text", text)
            val fragment = ComponentsFragment()
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


    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.components, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//            holder.binding.itemImage.setImageResource(R.drawable.bgs)
//            holder.binding.itemTitle.text = "架构师"
//            holder.binding.itemMessage.text = "哇哈哈"
        }

        override fun getItemCount(): Int {
            return 6
        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        lateinit var binding: ComponentsBinding
//        fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//            binding = ComponentsBinding.inflate(inflater, container, false)
//            val view = binding.root
//            return view
//        }
    }
}


