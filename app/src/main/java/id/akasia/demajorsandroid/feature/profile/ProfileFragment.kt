package id.akasia.demajorsandroid.feature.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.akasia.demajorsandroid.base.BaseFragment
import id.akasia.demajorsandroid.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<ProfileViewModel>() {
    override val viewModelClass: Class<ProfileViewModel> = ProfileViewModel::class.java
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
