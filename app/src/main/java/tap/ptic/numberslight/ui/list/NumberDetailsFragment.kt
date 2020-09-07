package tap.ptic.numberslight.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tap.ptic.numberslight.R
import tap.ptic.numberslight.data.db.entity.NumberDetails
import tap.ptic.numberslight.di.ViewModelProviderFactory
import timber.log.Timber
import javax.inject.Inject

class NumberDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private val viewModel: NumberDetailsViewModel by viewModels {
        viewModelProviderFactory
    }

    private val compositeDisposable = CompositeDisposable()


    private lateinit var imageView: ImageView
    private lateinit var detailsText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.number_details_fragment, container, false)
        imageView = view.findViewById(R.id.detailsFragment_image)
        detailsText = view.findViewById(R.id.detailsFragment_text)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getArgs()

        compositeDisposable.add(
            viewModel.selectedNumber.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { numberDetails ->
                    numberDetails?.let {
                        Timber.tag("NOPE").d("Selected value: ${numberDetails.name}")
                        updateUI(numberDetails)
                    }
                }
        )
        compositeDisposable.add(
            viewModel.selectedNumberFromList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { numberEntityItem ->
                    numberEntityItem?.let {
                        Timber.tag("NOPE").d("Choosen item: ${numberEntityItem.name}")
                        viewModel.getSpecificNumberInfo(numberEntityItem.name)
                    }
                }
        )

        compositeDisposable.add(viewModel.selectedNumberFromList.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                Timber.tag("NOPE").d("NumberEntityItem value: $it")
            }
        )


    }

    private fun getArgs() {
        val args = arguments?.let { NumberDetailsFragmentArgs.fromBundle(it) }
        val name = args?.name
        name?.let { viewModel.getSpecificNumberInfo(name) }
    }

    private fun updateUI(numberDetails: NumberDetails) {
        Picasso.with(context)
            .load(numberDetails.image)
            .into(imageView)
        detailsText.text = numberDetails.text
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}