package com.example.room_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.room_test.databinding.FragmentAddTaskBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater)
        initListeners()
        loadTasks()
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddTaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun saveTask(task: String) {
        val dao = TaskDatabase.getDatabase(requireContext()).getTask()
        val taskToAdd = Task(task)
        GlobalScope.launch { dao.addTask(taskToAdd) }
    }


    private fun loadTasks() {
        val dao = TaskDatabase.getDatabase((requireContext())).getTask()
        GlobalScope.launch { val tasks = dao.getTasks()
            val tasksAsText = tasks.joinToString ("\n") { it.name }
            binding.tvShowTasks.text = tasksAsText
        }
    }

    private fun initListeners() {
        binding.addTaskButton.setOnClickListener{
            saveTask(binding.etAddTask.text.toString())
            binding.etAddTask.setText("")
            Toast.makeText(context, "tarea añadida", Toast.LENGTH_LONG).show()
        }
    }
}