package io.github.leopold.service;

import io.github.leopold.domain.Contract;
import io.github.leopold.domain.NewContract;

public interface ContractService {

    Contract createContract(NewContract domain);

}
