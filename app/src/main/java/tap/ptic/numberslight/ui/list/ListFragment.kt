package tap.ptic.numberslight.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.list_fragment.view.*
import tap.ptic.numberslight.R
import tap.ptic.numberslight.data.db.entity.NumberEntityItem
import tap.ptic.numberslight.di.ViewModelProviderFactory
import tap.ptic.numberslight.ui.NumbersActivity
import tap.ptic.numberslight.ui.adapter.NumbersRecyclerAdapter
import javax.inject.Inject


class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val listViewModel: ListViewModel by viewModels {
        viewModelProviderFactory
    }

    private val numbersViewModel by viewModels<NumberDetailsViewModel>({ activity as NumbersActivity }) { viewModelProviderFactory }

    private lateinit var recyclerView: RecyclerView
    private var isDualPane: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        recyclerView = view.findViewById(R.id.listFragment_recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isDualPane = view?.numberDetails_fragment != null

        listViewModel.data.observe(viewLifecycleOwner, Observer {
            it?.let {
                setupRecycler(it)
                setupRecyclerListener(it)
            }
        })
    }

    private fun setupRecycler(list: List<NumberEntityItem>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = NumbersRecyclerAdapter(requireContext(), isDualPane)
        (recyclerView.adapter as NumbersRecyclerAdapter).setNumbers(list)
    }

    private fun setupRecyclerListener(list: List<NumberEntityItem>) {
        (recyclerView.adapter as NumbersRecyclerAdapter).initListener(object :
            NumbersRecyclerAdapter.OnNumberClickListener {
            override fun onItemClick(position: Int, view: View) {
                val action =
                    ListFragmentDirections.actionListFragmentToNumberDetailsFragment(list[position].name)
                if (isDualPane) {
                    numberDetails_fragment.visibility = View.VISIBLE
                    (recyclerView.adapter as NumbersRecyclerAdapter).setSelected(list[position])
                    numbersViewModel.selectedNumberFromList.onNext(list[position])
                } else {
                    numbersViewModel.selectedNumberFromList.onNext(list[position])
                    findNavController().navigate(action)
                }
            }

        })
    }
}