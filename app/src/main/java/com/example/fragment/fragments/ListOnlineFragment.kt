package com.example.fragment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.example.fragment.ProductMapper
import com.example.fragment.R
import com.example.fragment.dao.Product
import com.example.fragment.dao.ResponseProduct
import com.example.fragment.databinding.FragmentListOnlineBinding
import com.example.fragment.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListOnlineFragment : BottomSheetDialogFragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentListOnlineBinding
    private lateinit var composeView: ComposeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        return ComposeView(requireContext()).apply {
//            setContent {
//                MyComposeUI(mutableListOf())
//            }
//        }
        binding = FragmentListOnlineBinding.inflate(inflater, container, false)
        composeView = binding.listOnlineCompose
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            mainViewModel._productDataState.collect { data ->
                val productsData = data.data
                setProducts(productsData)
            }
        }
    }

    private fun setProducts( productsData: ResponseProduct?) {
        composeView.apply {
            setContent {
                if (productsData!!.size > 0) {
                    MyComposeUI(productsData.map {
                        ProductMapper().buildProduct(it)
                    })
                }
            }
        }
    }
}


@Composable
fun MyComposeUI(products: List<Product>) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(modifier= Modifier.fillMaxSize()) {
            items(products){
                ProductItemRow(
                    it.title,
                    it.image
                )
            }
        }
    }
}


@Composable
fun ProductItemRow(
    title: String,
    imageSrc:String
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { },
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = imageSrc,
                contentDescription = "Product image",
                modifier=Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            )
            Divider(
                color= colorResource(id = R.color.gray),
                thickness=1.dp
            )
            Text(
                text = title, color = colorResource(R.color.lightgray), fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
        }
    }
}


